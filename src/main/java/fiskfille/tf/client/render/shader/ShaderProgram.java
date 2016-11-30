package fiskfille.tf.client.render.shader;

import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBGeometryShader4;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ShaderProgram
{
    private static final FloatBuffer MATRIX_BUFFER = BufferUtils.createFloatBuffer(16);

    private static final List<ShaderProgram> PROGRAMS = new ArrayList<ShaderProgram>();

    private Map<String, Integer> uniforms = new HashMap<String, Integer>();

    private int programID;
    private int vertexShaderID;
    private int geometryShaderID;
    private int fragmentShaderID;

    private boolean hasGeometryShader;

    public ShaderProgram(String vertex, String fragment, String geometry) throws Exception
    {
        this.hasGeometryShader = geometry != null;
        this.vertexShaderID = ShaderProgram.loadShader(vertex, ARBVertexShader.GL_VERTEX_SHADER_ARB);
        this.fragmentShaderID = ShaderProgram.loadShader(fragment, ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);

        if (this.hasGeometryShader)
        {
            this.geometryShaderID = ShaderProgram.loadShader(geometry, ARBGeometryShader4.GL_GEOMETRY_SHADER_ARB);
        }

        this.programID = OpenGlHelper.func_153183_d();

        OpenGlHelper.func_153178_b(this.programID, this.vertexShaderID);
        OpenGlHelper.func_153178_b(this.programID, this.fragmentShaderID);

        if (this.hasGeometryShader)
        {
            OpenGlHelper.func_153178_b(this.programID, this.geometryShaderID);
        }

        this.bindAttributes();

        OpenGlHelper.func_153179_f(this.programID);

        if (OpenGlHelper.func_153175_a(this.programID, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE)
        {
            throw new RuntimeException("Error creating shader: " + ShaderProgram.getLogInfoProgram(this.programID));
        }

        GL20.glValidateProgram(this.programID);

        if (OpenGlHelper.func_153175_a(this.programID, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE)
        {
            throw new RuntimeException("Error creating shader: " + getLogInfoProgram(this.programID));
        }

        PROGRAMS.add(this);

        for (String uniform : this.getUniforms())
        {
            int location = this.getUniformLocation(uniform);

            if (location == -1)
            {
                System.err.println("Could not find uniform location for " + uniform + " in " + this.getClass().getSimpleName() + "!");
            }
            else if (this.uniforms.containsValue(location))
            {
                System.err.println("Duplicate uniform location for " + uniform + " in " + this.getClass().getSimpleName() + "!");
            }

            this.uniforms.put(uniform, location);
        }

        this.stop();
    }

    public ShaderProgram(String vertex, String fragment) throws Exception
    {
        this(vertex, fragment, null);
    }

    protected abstract void bindAttributes();

    protected abstract String[] getUniforms();

    protected void bindAttribute(int index, String name)
    {
        GL20.glBindAttribLocation(this.programID, index, name);
    }

    protected int getUniformLocation(String name)
    {
        return OpenGlHelper.func_153194_a(this.programID, name);
    }

    public void setUniform(String name, float value)
    {
        GL20.glUniform1f(this.uniforms.get(name), value);
    }

    public void setUniform(String name, int value)
    {
        GL20.glUniform1i(this.uniforms.get(name), value);
    }

    public void setUniform(String name, Vector3f value)
    {
        GL20.glUniform3f(this.uniforms.get(name), value.x, value.y, value.z);
    }

    public void setUniform(String name, boolean value)
    {
        GL20.glUniform1f(this.uniforms.get(name), value ? 1.0F : 0.0F);
    }

    public void setUniform(String name, Matrix4f value)
    {
        value.store(MATRIX_BUFFER);
        MATRIX_BUFFER.flip();
        GL20.glUniformMatrix4(this.uniforms.get(name), false, MATRIX_BUFFER);
    }

    public void setUniform(String name, Vector4f value)
    {
        GL20.glUniform4f(this.uniforms.get(name), value.x, value.y, value.z, value.w);
    }

    public void start()
    {
        OpenGlHelper.func_153161_d(this.programID);
    }

    public void stop()
    {
        OpenGlHelper.func_153161_d(0);
    }

    public void delete()
    {
        this.stop();

        GL20.glDetachShader(this.programID, this.vertexShaderID);
        GL20.glDetachShader(this.programID, this.fragmentShaderID);

        if (this.hasGeometryShader)
        {
            GL20.glDetachShader(this.programID, this.geometryShaderID);
        }

        OpenGlHelper.func_153180_a(this.vertexShaderID);
        OpenGlHelper.func_153180_a(this.fragmentShaderID);

        if (this.hasGeometryShader)
        {
            OpenGlHelper.func_153180_a(this.geometryShaderID);
        }

        OpenGlHelper.func_153187_e(this.programID);
        PROGRAMS.remove(this);
    }

    public static int loadShader(String resource, int type) throws Exception
    {
        String source = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(ShaderProgram.class.getResourceAsStream("/assets/transformers/shaders/" + resource)));

        String line;

        while ((line = in.readLine()) != null)
        {
            source += line + "\n";
        }

        int shaderID = OpenGlHelper.func_153195_b(type);
        byte[] bytes = source.getBytes();
        ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length).put(bytes);
        buffer.flip();
        OpenGlHelper.func_153169_a(shaderID, buffer);
        OpenGlHelper.func_153170_c(shaderID);

        if (OpenGlHelper.func_153157_c(shaderID, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE)
        {
            System.err.println("Failed to compile shader: " + resource);
            System.err.println(getLogInfoShader(shaderID));
        }

        return shaderID;
    }

    public static void deletePrograms()
    {
        int size = PROGRAMS.size();

        for (int i = 0; i < size; i++)
        {
            PROGRAMS.get(0).delete();
        }

        PROGRAMS.clear();
    }

    private static String getLogInfoShader(int shader)
    {
        return OpenGlHelper.func_153158_d(shader, OpenGlHelper.func_153157_c(shader, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    private static String getLogInfoProgram(int program)
    {
        return OpenGlHelper.func_153166_e(program, OpenGlHelper.func_153175_a(program, ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }
}
