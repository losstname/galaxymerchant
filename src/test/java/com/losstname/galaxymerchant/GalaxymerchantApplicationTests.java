package com.losstname.galaxymerchant;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.losstname.galaxymerchant.GalaxymerchantApplication.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GalaxymerchantApplicationTests {

    private final ByteArrayOutputStream outContent;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    GalaxymerchantApplicationTests() {
        outContent = new ByteArrayOutputStream();
    }

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

	@Test
	void testFindValueOfRoman() {
        tokenRomanValueMapping.put("tegj","L");
        tokenRomanValueMapping.put("glob","I");
        tokenRomanValueMapping.put("prok","V");
        tokenRomanValueMapping.put("pish","X");
        questionAndReply.clear();
        questionAndReply.put("how much is pish tegj glob glob ?","");
        OutputProcess.processReplyForQuestion();
        assertEquals("how much is pish tegj glob glob ? pish tegj glob glob is 42.0\n", outContent.toString());
	}

    @Test
    void testFindValueOfElement() {
        tokenRomanValueMapping.put("tegj","L");
        tokenRomanValueMapping.put("glob","I");
        tokenRomanValueMapping.put("prok","V");
        tokenRomanValueMapping.put("pish","X");
        elementValueList.put("Silver",17.0f);
        elementValueList.put("Gold",14450.0f);
        elementValueList.put("Iron",195.5f);
        questionAndReply.clear();
        questionAndReply.put("how many Credits is glob prok Iron ?","");
        OutputProcess.processReplyForQuestion();
        assertEquals("how many Credits is glob prok Iron ? glob prok Iron is 782.0 Credits\n", outContent.toString());
    }

    @Test
    void testFindValueOfElement1() {
        tokenRomanValueMapping.put("tegj","L");
        tokenRomanValueMapping.put("glob","I");
        tokenRomanValueMapping.put("prok","V");
        tokenRomanValueMapping.put("pish","X");
        elementValueList.put("Silver",17.0f);
        elementValueList.put("Gold",14450.0f);
        elementValueList.put("Iron",195.5f);
        questionAndReply.clear();
        questionAndReply.put("how many Credits is glob prok Gold ?","");
        OutputProcess.processReplyForQuestion();
        assertEquals("how many Credits is glob prok Gold ? glob prok Gold is 57800.0 Credits\n", outContent.toString());
    }

    @Test
    void testFindValueOfElement2() {
        tokenRomanValueMapping.put("tegj","L");
        tokenRomanValueMapping.put("glob","I");
        tokenRomanValueMapping.put("prok","V");
        tokenRomanValueMapping.put("pish","X");
        elementValueList.put("Silver",17.0f);
        elementValueList.put("Gold",14450.0f);
        elementValueList.put("Iron",195.5f);
        questionAndReply.clear();
        questionAndReply.put("how many Credits is glob prok Silver ?","");
        OutputProcess.processReplyForQuestion();
        assertEquals("how many Credits is glob prok Silver ? glob prok Silver is 68.0 Credits\n", outContent.toString());
    }

}
