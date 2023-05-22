package exercise;

// BEGIN
class ReversedSequence implements CharSequence {
    String text;

    public ReversedSequence(String text) {
        StringBuilder sb = new StringBuilder(text);
        this.text = sb.reverse().toString();
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public char charAt(int i) {
        return text.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return text.subSequence(i, i1).toString();
    }
}
// END
