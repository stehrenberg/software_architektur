package edu.hm.iny.patterns.decorators.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	StringPictureTest.class,
	ModernTest.class,
	ClocktimeImageTest.class,
	RotatedTextImageTest.class,
	FramedTextImageTest.class,
	TiledTextImageTest.class,
	DistantTextImageTest.class,
	PrintableImageTest.class})
public class TextImageTestSuite {

}
