#version 330 core
in vec3 vertColor;

in vec3 vertLightOutVector;
in vec3 surfaceNormal;

out vec4 frColor;

uniform vec3 lightColor;

void main() {

    vec3 unitNorm  = normalize(surfaceNormal);
    vec3 unitLight = normalize(vertLightOutVector);

    float dotPr = dot(unitLight,unitNorm);
    float brightness = max(dotPr,0.2);

    vec3 diffuse = brightness * lightColor;

    frColor = vec4(diffuse,1.0);
}

