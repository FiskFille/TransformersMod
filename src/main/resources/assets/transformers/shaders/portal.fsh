#version 120

varying in vec4 vertexPosition;

uniform float time;
uniform sampler2D textureSampler;

const float rippleAmount = 0.04;

void main(void) {
    //float timeScaled = time * 0.1;
    //float offsetX = sin((texture2D(textureSampler, vec2((sin(vertexPosition.x * vertexPosition.y + vertexPosition.x * vertexPosition.y * sin(timeScaled) * 5.0)) * 0.01, (vertexPosition.y * sin(timeScaled) * 5.0) * 0.01)).r - 0.5) * 0.5);
    //float offsetY = ((texture2D(textureSampler, vec2(vertexPosition.x * sin(offsetX) * 0.01, vertexPosition.y * 0.01)).r - 0.5) * 0.5);
    //vec3 distortedPosition = vec3(vertexPosition.x + offsetX, vertexPosition.y, vertexPosition.z - offsetY);
    //float centerDistance = (distortedPosition.x * distortedPosition.x + distortedPosition.y * distortedPosition.y + (cos(distortedPosition.z * distortedPosition.z) * 2));
    //float alpha = clamp(sin(cos(timeScaled) * centerDistance), 0.5, 1.0);
    //float edge = 1.0 - clamp((centerDistance * 0.05 - 0.2) * 15.0, 0.0, 1.0);
    //gl_FragColor = vec4(clamp(sin(centerDistance + timeScaled), 0.2, 0.6), clamp(cos(centerDistance + timeScaled), 0.8, 1.0), 0.7, alpha * edge);
    float timeScaled = time * 0.1;
    vec4 distortedPosition = vertexPosition;

    vec2 scale = vec2(0.1, 0.1);

    vec2 distortedTexCoords = texture2D(textureSampler, vec2(vertexPosition.x + timeScaled, vertexPosition.y)).rg * 0.1;
    distortedTexCoords = (vertexPosition.xy) + (vec2(distortedTexCoords.x, distortedTexCoords.y + timeScaled));
    vec2 rippleDirection = (texture2D(textureSampler, distortedTexCoords* scale).rg * 2.0 - 1.0) * rippleAmount;

    distortedPosition += vec4(rippleDirection.x * 2.0, rippleDirection.y * 2.0, 0.0, 0.0);

    float centerDistance = (distortedPosition.x * distortedPosition.x + distortedPosition.y * distortedPosition.y + (cos(distortedPosition.z * distortedPosition.z) * 2));
    float alpha = clamp(sin(cos(timeScaled) * centerDistance), 0.5, 1.0);
    float edge = 1.0 - clamp((centerDistance * 0.05 - 0.2) * 15.0, 0.0, 1.0);
    gl_FragColor = vec4(clamp(sin(centerDistance + timeScaled), 0.2, 0.6), clamp(cos(centerDistance + timeScaled), 0.8, 1.0), 0.7, alpha * edge);
}