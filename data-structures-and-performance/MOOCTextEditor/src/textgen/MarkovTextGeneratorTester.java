package textgen;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MarkovTextGeneratorTester {

	MarkovTextGeneratorLoL generator;
	String shortText;

	@Before
	public void setUp() throws Exception {
		generator = new MarkovTextGeneratorLoL(new Random(42));
		shortText = "hi there hi Leo";
	}

	@Test
	public void testTrain() {
		generator.train(shortText);
		String a = "hi: there->Leo->\nthere: hi->\nLeo: hi->";
		assertEquals("testTrain: check wordList ", a, generator);
	}

}
