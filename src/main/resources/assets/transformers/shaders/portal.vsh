#version 120

varying vec4 vertexPosition;

void main(void) {
    gl_TexCoord[0] = gl_MultiTexCoord0;
    gl_TexCoord[1] = gl_MultiTexCoord1;
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
    vertexPosition = gl_Vertex;
}