package dk.nindroid.rss.renderers;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import dk.nindroid.rss.gfx.Vec3f;

/**
 * Created by Alex on 1/1/2016.
 */
public class GLHelper {
    private static final int VERTS = 4;

    public static IntBuffer createVertices() {
        int one = 0x10000;
        int vertices[] = {
                -one,  one, -one,
                -one, -one, -one,
                one,  one, -one,
                one, -one, -one
        };

        /*
        Vec3f[] fvertices = new Vec3f[4];
        for(int i = 0; i < 4; ++i){
            Vec3f p = new Vec3f(vertices[i*3] / one, vertices[i*3 + 1] / one, vertices[i*3 + 2] / one);
            fvertices[i] = p;
        }
        */

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        IntBuffer vertexBuffer = vbb.asIntBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        return vertexBuffer;
    }

    public static ByteBuffer createIndexBuffer() {
        byte indices[] = {
                0, 1, 2, 3
        };

        ByteBuffer indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);

        return indexBuffer;
    }

    public static FloatBuffer createTexBuffer(float[] tex) {
        ByteBuffer tbb = ByteBuffer.allocateDirect(VERTS * 2 * 4);
        tbb.order(ByteOrder.nativeOrder());
        FloatBuffer texBuffer = tbb.asFloatBuffer();

        /*
        float tex[] = {
                0.0f,  0.0f,
                0.0f,  0.0f,
                0.0f,  0.0f,
                0.0f,  0.0f,
        };
        */
        texBuffer.put(tex);
        texBuffer.position(0);

        return texBuffer;
    }

    public static int createTexture(GL10 gl) {
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        int textureID = textures[0];
        return textureID;
    }

    public static void setTexture(GL10 gl, Bitmap bmp, int textureID) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_CLAMP_TO_EDGE);

        gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE,
                GL10.GL_BLEND);

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
    }

    public static void draw(GL10 gl, int textureID, IntBuffer vertexBuffer, FloatBuffer texBuffer, ByteBuffer indexBuffer) {
        gl.glActiveTexture(GL10.GL_TEXTURE0);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureID);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_BYTE, indexBuffer);
    }
}
