package exercise;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReversedSequenceTest {

    @Test
    void length() {
        ReversedSequence rs = new ReversedSequence("abcdef");
        int extendLength = 6;
        assertThat(rs.length()).isEqualTo(extendLength);
    }

    @Test
    void charAt() {
        ReversedSequence rs = new ReversedSequence("abcdef");
        char extendChar = 'e';
        assertThat(rs.charAt(1)).isEqualTo(extendChar);
    }

    @Test
    void subSequence() {
        ReversedSequence rs = new ReversedSequence("abcdef");
        String extendText = "fedcba";
        assertThat(rs.text).isEqualTo(extendText);
    }
}
