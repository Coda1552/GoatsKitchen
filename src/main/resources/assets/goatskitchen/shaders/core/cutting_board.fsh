#version 150

#define CUT_WIDTH 0.008

uniform sampler2D Sampler0;
uniform vec4 ColorModulator;

uniform vec3 CutData;

in vec2 texCoord0;
in vec2 position;

out vec4 fragColor;

float line(vec2 p, vec2 a,vec2 b) { // --- distance to segment with caps
    p -= a, b -= a;
    float h = clamp(dot(p, b) / dot(b, b), 0., 1.);// proj coord on line
    return length(p - b * h);                      // dist to segment
    // We might directly return smoothstep( 3./R.y, 0., dist),
    //     but its more efficient to factor all lines.
    // We can even return dot(,) and take sqrt at the end of polyline:
    // p -= b*h; return dot(p,p);
}

void main() {
    // Basic input data
    vec2 point = CutData.xy;
    float angle = CutData.z;
    point.y = 1 - point.y;

    // Helpful calculated data
    vec2 direction = vec2(cos(angle), sin(angle));
    vec2 offsetDirection = vec2(cos(angle + 3.14 / 2), sin(angle + 3.14 / 2));
    vec2 lineOffset = vec2(0, abs(angle - 3.14 / 2) * CUT_WIDTH * 8);
    float distance = length(point - position);

    // Create 2 points based on the center point and angle
    vec2 linePointA = point + direction * distance;
    vec2 linePointB = point - direction * distance;

    // Which direction to offset in
    float sign;
    float d = dot(normalize(point - position), offsetDirection);

    if (d < -0.01) {
        sign = -1;
    } else if (d > 0.01) {
        sign = 1;
    } else {
        sign = 0;
    }

    if (line(position, linePointA, linePointB) < CUT_WIDTH) {
        fragColor = vec4(0);
    } else {
        vec2 modifiedTexCoord = texCoord0 + (direction * sign + lineOffset) * CUT_WIDTH;
        if (modifiedTexCoord.x < 0 || modifiedTexCoord.x > 1 || modifiedTexCoord.y < 0 || modifiedTexCoord.y > 1) {
            fragColor = vec4(0);
        } else {
            fragColor = texture(Sampler0, modifiedTexCoord) * ColorModulator;
        }
    }
}
