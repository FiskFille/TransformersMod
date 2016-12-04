#version 120

varying vec4 vertexPosition;

uniform float time;
uniform sampler2D textureSampler;
uniform sampler2D lightning;

void main(void) {
    float timeScaled = time * 0.1;
    vec4 distortedPosition = vertexPosition;

    vec2 scale = vec2(0.1, 0.1);

    vec2 distortedTexCoords = texture2D(textureSampler, vec2(vertexPosition.x + timeScaled, vertexPosition.y - timeScaled)).rg * 0.1;
    distortedTexCoords = (vertexPosition.xy) + (vec2(distortedTexCoords.x, distortedTexCoords.y + timeScaled));
    vec2 rippleDirection = (texture2D(textureSampler, distortedTexCoords * scale).rg * 2.0 - 1.0) * 0.06;

    distortedPosition += vec4(rippleDirection.x * 5.0, rippleDirection.y * 5.0, 0.0, 0.0);

    float centerDistanceDistorted = (distortedPosition.x * distortedPosition.x + distortedPosition.y * distortedPosition.y + (cos(distortedPosition.z * distortedPosition.z) * 2));
    float centerDistance = (vertexPosition.x * vertexPosition.x + vertexPosition.y * vertexPosition.y + (cos(vertexPosition.z * vertexPosition.z) * 2));

    float brightness = sin(rippleDirection.x + rippleDirection.y);
    float brightnessEdge = clamp(sin(clamp((centerDistanceDistorted - 0.7) / 2, 0.0, 4.0)) + clamp((cos(timeScaled) + 1.0) * 0.15, 0.0, 0.3), 0.7, 1.0);
    float alphaEdge = 1.0 - clamp(sin((centerDistanceDistorted - 3.3) / 2), 0.0, 1.0);
    float alphaEdgeNonDistorted = 1.0 - clamp(sin((centerDistance - 3.3) / 2) * 2, 0.0, 1.0);
    float greenPulse = clamp(sin(timeScaled + (centerDistanceDistorted * 0.3)) * 0.1, -0.1, 0.1);

    float mixedBrightness = clamp(brightnessEdge + brightness, 0.0, 1.0);
    gl_FragColor = vec4(0.4 - greenPulse, 0.8, 0.7 - greenPulse, 1.0) * vec4(brightnessEdge, mixedBrightness, brightnessEdge, alphaEdge * alphaEdgeNonDistorted);
}