public int flipBit(int num) {
    if (~num == 0) return 32;

    int currentLength = 0;
    int previousLength = 0;
    int maxLength = 1;

    while (num != 0) {
        if ((num & 1) == 1) {
            currentLength++;
        } else {
            previousLength = (num & 2) == 0 ? 0 : currentLength;
            currentLength = 0;
        }

        maxLength = Math.max(previousLength + currentLength + 1, maxLength);
        num >>>= 1;
    }

    return maxLength;
}