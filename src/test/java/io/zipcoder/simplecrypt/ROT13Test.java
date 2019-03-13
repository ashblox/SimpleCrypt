package io.zipcoder.simplecrypt;

import com.sun.tools.javac.comp.Flow;
import io.zipcoder.simplecrypt.ROT13;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ROT13Test {


    @Test
    public void rotateStringTest0() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "ABCDEF";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'A');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest1() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "DEFABC";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'D');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest2() {
        // Given
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = "NOPQRSTUVWXYZABCDEFGHIJKLM";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'N');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void cryptTest1() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";
        String A1 = "Jul qvq gur puvpxra pebff gur ebnq?";

        String Q2 = "Gb trg gb gur bgure fvqr!";
        String A2 = "To get to the other side!";

        // When
        String actual = cipher.encrypt(Q1);

        // Then
        assertTrue(actual.equals(A1));

        // When
        String actual2 = cipher.decrypt(Q2);

        // Then
        assertTrue(actual2.equals(A2));
    }
    @Test
    public void cryptTest2() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";

        // When
        String actual = cipher.crypt(cipher.crypt(Q1));

        // Then
        assertTrue(actual.equals(Q1));
    }

    @Test
    public void isAlphabeticalTest1() {
        // Given
        Character c = '?';

        // When, Then
        Assert.assertFalse(ROT13.isAlphabetical(c));
    }

    @Test
    public void isAlphabeticalTest2() {
        // Given
        Character c = 'B';

        // When, Then
        Assert.assertTrue(ROT13.isAlphabetical(c));
    }

    @Test
    public void encryptCustomCypherTest() {
        // Given
        ROT13 customCypher = new ROT13('f', 'n');
        String text = "You passed the test!";
        String expected = "Gwc xiaaml bpm bmab!";

        // When
        String actual = customCypher.encrypt(text);

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void decryptCustomCypherTest() {
        // Given
        ROT13 customCypher = new ROT13('f', 'n');
        String text = "Gwc xiaaml bpm bmab!";
        String expected = "You passed the test!";

        // When
        String actual = customCypher.decrypt(text);

        //Then
        Assert.assertEquals(expected, actual);
    }

}