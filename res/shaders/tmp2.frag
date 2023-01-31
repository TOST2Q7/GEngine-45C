#version 430 core

layout (location = 0) out vec4 out_Colour;

in vec3 position;

void main()
{
    out_Colour = vec4(position+0.3f, 1.0f);
}