package fiskfille.tf.client.render.shader;

public class PortalShader extends ShaderProgram
{
    public PortalShader() throws Exception
    {
        super("portal.vsh", "portal.fsh");
    }

    @Override
    protected void bindAttributes()
    {
    }

    public void setTime(float time)
    {
        this.setUniform("time", time);
    }

    @Override
    protected String[] getUniforms()
    {
        return new String[] { "time" };
    }
}
