

public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
    int bytesPerRow = width / 8;
    int rowOffset = y * bytesPerRow;

    int startByte = x1 / 8;
    int endByte = x2 / 8;

    int startOffset = x1 % 8;
    int endOffset = x2 % 8;

    // fill full bytes
    for (int b = startByte + 1; b < endByte; b++) {
        screen[rowOffset + b] = (byte) 0xFF;
    }

    // masks
    byte startMask = (byte) (0xFF >> startOffset);
    byte endMask = (byte) ~(0xFF >> (endOffset + 1));

    if (startByte == endByte) {
        byte mask = (byte) (startMask & endMask);
        screen[rowOffset + startByte] |= mask;
    } else {
        screen[rowOffset + startByte] |= startMask;
        screen[rowOffset + endByte] |= endMask;
    }
}