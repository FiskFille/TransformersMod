#version 120

varying vec4 vertexPosition;

uniform float time;
uniform sampler2D textureSampler;

void main(void) {
    float timeScaled = time * 0.1;
    vec4 distortedPosition = vertexPosition;

    vec2 scale = vec2(0.1, 0.1);

    vec2 distortedTexCoords = texture2D(textureSampler, vec2(vertexPosition.x + timeScaled, vertexPosition.y)).rg * 0.1;
    distortedTexCoords = (vertexPosition.xy) + (vec2(distortedTexCoords.x, distortedTexCoords.y + timeScaled));
    vec2 rippleDirection = (texture2D(textureSampler, distortedTexCoords * scale).rg * 2.0 - 1.0) * 0.06;

    distortedPosition += vec4(rippleDirection.x * 2.0, rippleDirection.y * 2.0, 0.0, 0.0);

    float centerDistance = (distortedPosition.x * distortedPosition.x + distortedPosition.y * distortedPosition.y + (cos(distortedPosition.z * distortedPosition.z) * 2));
    float pulsate = sin(centerDistance + timeScaled);
    float pulsate2 = cos(timeScaled - centerDistance);
    float alpha = clamp(pulsate + sin(rippleDirection.x + timeScaled), 0.5, 1.0);
    float edge = 1.0 - clamp((centerDistance * 0.05 - 0.2) * 15.0, 0.0, 1.0);
    gl_FragColor = vec4(clamp(pulsate + pulsate2, 0.2, 0.6), clamp(pulsate + pulsate2, 0.8, 1.0), 0.7, alpha * edge);
}