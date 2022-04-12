import java.util.*;

class AsciiCharSequence implements CharSequence {
    private final byte[] arr;

    public AsciiCharSequence(byte[] inArr) {
        arr = new byte[inArr.length];

        System.arraycopy(inArr, 0, arr, 0, inArr.length);
    }

    public int length() {
        return arr.length;
    }

    public char charAt(int idx) {
        return (char) arr[idx];
    }

    public CharSequence subSequence(int from, int to) {
        byte[] subArr = new byte[to - from];
        int it = from;
        for (int i = 0; i < to - from; i++) {
            subArr[i] = (byte) arr[it];
            it++;
        }
        return new AsciiCharSequence(subArr);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (byte b : arr) {
            str.append((char) b);
        }
        return str.toString();
    }

    // implementation
}