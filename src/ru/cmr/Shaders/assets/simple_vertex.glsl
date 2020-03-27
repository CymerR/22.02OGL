#version 400

layout(location = 0) in vec3 evPos;
layout(location = 1) in vec3 evColor;
layout(location = 2) in vec2 evTexCoord;


//out vec4 ovPos;
out vec4 ovCol;

void main() {
    gl_Position = vec4(evPos,1.0);
    ovCol = vec4(evColor,1.0);
}
