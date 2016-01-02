package dk.nindroid.rss.renderers;

import android.graphics.Bitmap;

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
    }

    public static void setTexture(GL10 gl, Bitmap mBmp, int mTextureID) {
    }

    public static void draw(GL10 gl, int mTextureID, IntBuffer mVertexBuffer, FloatBuffer mTexBuffer, ByteBuffer mIndexBuffer) {

    }
}
