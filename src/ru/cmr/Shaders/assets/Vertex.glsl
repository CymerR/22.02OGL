#version 330 core

layout(location = 0) in vec3 vertPosition;
layout(location = 1) in vec3 vertColorin;
layout(location = 2) in vec3 normal;

out vec3 vertColor;

out vec3 vertLightOutVector;
out vec3 surfaceNormal;


uniform mat4 projection;
uniform mat4 transform;
uniform mat4 view;
uniform vec3 testUniColor;
//uniform vec3 lightPos;

void main() {

    vec4 worldPosition = transform * vec4(vertPosition, 1.0);
    gl_Position = projection * view * worldPosition;

    vec3 lightPos = vec3(0,0,0);

    surfaceNormal = (transform * vec4(normal,0.0)).xyz;
    vertLightOutVector=(lightPos-worldPosition.xyz).xyz;

    vertColor = vec3(0,1,0);
}

