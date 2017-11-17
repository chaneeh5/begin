package com.cookbook.open_gl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

class Pyramid
{
    public Pyramid()
    {
        int one = 0x10000;
        /* square base and point top to make a pyramid */
        int vertices[] = {
                -one, -one, -one,
                -one,  one, -one,
                one,  one,  -one,                
                one,  -one,  -one,
                0, 0, one
        };

        /* purple fading to white at the top */
        int colors[] = {
                one, 0, one, one,
                one, 0, one, one,
                one, 0, one, one,
                one, 0, one, one,
                one, one, one, one
        };

        /* triangles of the vertices above to build the shape */ 
        byte indices[] = {
                0, 1, 2,  0, 2, 3, //square base
                0, 3, 4, // side 1
                0, 4, 1, // side 2
                1, 4, 2, // side 3
                2, 4, 3  // side 4
        };

        // Buffers to be passed to gl*Pointer() functions
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asIntBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asIntBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, GL10.GL_FIXED, 0, mColorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, 18, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
    }

    private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;
}