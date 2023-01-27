// This is JUnit test program stub
// DO NOT CHANGE THE NAME OF THE METHODS GIVEN
// 0) test0 is by the instructor as example to test your validate() method
// 1) You are to reproduce testing validate() method with test1.html-test8.html and
//    match the expected output
// 2) You are to add your own JUnit test for testing your removeAll method (At least 4)
// 3) Feel free to add more test cases to test any of your public methods in HtmlValidator (No extra credit, but for your own benefit)

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class HtmlValidatorTest {
    /**
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
     *                         expectedFileName should be "tst/YOUR_FILE_NAME"
     * @return The String format of what the expectedFileName contains
     */

    //takes a file name as input and returns the contents of that file as a string
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    //captures the output of the validate() method and returns it as a string
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }



    /**
     * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
     */
    @Test
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("b", true));      // <b>
        tags.add(new HtmlTags("b", false));     // </b>
        tags.add(new HtmlTags("br"));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("tst/test0_expected_output.txt"),
                            validatorOutputToString(validator));
    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test1.html and expected_output/validate_result_for_test1.txt
     */
	@Test
	public void test1(){
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("b", true));      // <b>
        tags.add(new HtmlTags("i", true));      // <i>
        tags.add(new HtmlTags("i", false));     // </i>
        tags.add(new HtmlTags("b", false));     // </b>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test1.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test2 method should test your validate() method
     * reproducing the test of
     * input_html/test2.html and expected_output/validate_result_for_test2.txt
     */
	@Test
	public void test2() {
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("html"));      // <html>
        tags.add(new HtmlTags("b", true));      // <b>
        tags.add(new HtmlTags("i", true));     // <i>
        tags.add(new HtmlTags("i", false));     // </i>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test2.txt"),
                validatorOutputToString(validator));
    }

    /**
     * This test3 method should test your validate() method
     * reproducing the test of
     * input_html/test3.html and expected_output/validate_result_for_test3.txt
     */
	@Test
	public void test3(){
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("b", true));      // <b>
        tags.add(new HtmlTags("i", true));      // <i>
        tags.add(new HtmlTags("b", false));     // </b>
        tags.add(new HtmlTags("i", false));     // </i>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test3.txt"),
                validatorOutputToString(validator));
	}


    /**
     * This test4 method should test your validate() method
     * reproducing the test of
     * input_html/test4.html and expected_output/validate_result_for_test4.txt
     */
	@Test
	public void test4(){
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("b", true));      // <b>
        tags.add(new HtmlTags("i", true));      // <i>
        tags.add(new HtmlTags("b", false));     // </b>
        tags.add(new HtmlTags("i", false));     // </i>
        tags.add(new HtmlTags("b", false));     // </b>
        tags.add(new HtmlTags("html", false));     // </html>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test4.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test5 method should test your validate() method
     * reproducing the test of
     * input_html/test5.html and expected_output/validate_result_for_test5.txt
     */
	@Test
	public void test5(){
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("html", false));     // </html>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test5.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test6.html and expected_output/validate_result_for_test6.txt
     */
	@Test
	public void test6(){
        Queue<HtmlTags> tags = new LinkedList<>();
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test6.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test7 method should test your validate() method
     * reproducing the test of
     * input_html/test7.html and expected_output/validate_result_for_test7.txt
     */
	@Test
	public void test7(){
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("!doctype"));                 // <!doctype>
        tags.add(new HtmlTags("!--"));                   // <!-- -->
        tags.add(new HtmlTags("html", true));     // <html>
        tags.add(new HtmlTags("head", true));     // <head>
        tags.add(new HtmlTags("title", true));    // <title>
        tags.add(new HtmlTags("title", false));   // </title>
        tags.add(new HtmlTags("meta"));                     // <meta>
        tags.add(new HtmlTags("link"));                     // <link>
        tags.add(new HtmlTags("head", false));    // </head>
        tags.add(new HtmlTags("body", true));     // <body>
        tags.add(new HtmlTags("p", true));        // <p>
        tags.add(new HtmlTags("a", true));        // <a>
        tags.add(new HtmlTags("a", false));       // </a>
        tags.add(new HtmlTags("p", false));       // </p>
        tags.add(new HtmlTags("p", true));        // <p>
        tags.add(new HtmlTags("img"));                      // <img>
        tags.add(new HtmlTags("p", false));       // </p>
        tags.add(new HtmlTags("body", false));    // </body>
        tags.add(new HtmlTags("html", false));    // </html>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test7.txt"),
                validatorOutputToString(validator));
	}

    /**
     * This test8 method should test your validate() method
     * reproducing the test of
     * input_html/test8.html and expected_output/validate_result_for_test8.txt
     */
	@Test
	public void test8(){
        Queue<HtmlTags> tags = new LinkedList<>();
        tags.add(new HtmlTags("!doctype"));                 // <!doctype>
        tags.add(new HtmlTags("!--"));                     // <!-- -->
        tags.add(new HtmlTags("html", true));     // <html>
        tags.add(new HtmlTags("head", true));     // <head>
        tags.add(new HtmlTags("title", true));    // <title>
        tags.add(new HtmlTags("meta"));                     // <meta>
        tags.add(new HtmlTags("link"));                     // <meta>
        tags.add(new HtmlTags("head", false));     // <head>
        tags.add(new HtmlTags("head", false));     // <head>
        tags.add(new HtmlTags("body", true));       // <body>
        tags.add(new HtmlTags("p", true));        // <p>
        tags.add(new HtmlTags("a", true));        // <a>
        tags.add(new HtmlTags("a", false));       // </a>
        tags.add(new HtmlTags("p", false));       // </p>
        tags.add(new HtmlTags("br", false));    // </br> honestly have no idea why no unexpected error occurs
        tags.add(new HtmlTags("p", true));        // <p>
        tags.add(new HtmlTags("img"));                        // <img>
        tags.add(new HtmlTags("p", false));       // </p>
        tags.add(new HtmlTags("html", false));       // </html>
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("tst/validate_result_for_test8.txt"),
                validatorOutputToString(validator));
	}

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
    public void removeAllTest1() {
        HtmlTags[] tagsArr = {new HtmlTags("Hello"), new HtmlTags("There")};
        List<HtmlTags> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTags("Bob"));
        validator.addTag(new HtmlTags("was"));
        validator.addTag(new HtmlTags("here"));

        validator.removeAll("Bob");
        validator.removeAll("was");
        validator.removeAll("heRE");

        Assert.assertEquals(tags, validator.getTags());
    }

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest2(){

        HtmlTags[] tagsArr = {new HtmlTags("Hello"), new HtmlTags("There")};
        List<HtmlTags> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTags("Bob"));

        validator.removeAll("Bob");
        validator.removeAll("was");
        validator.removeAll("heRE");

        Assert.assertEquals(tags, validator.getTags());
	}

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest3(){

        HtmlTags[] tagsArr = {new HtmlTags("Hello"), new HtmlTags("There")};
        List<HtmlTags> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTags("Bob"));
        validator.addTag(new HtmlTags("was"));
        validator.addTag(new HtmlTags("here"));

        validator.removeAll("Bob");
        validator.removeAll("was");
        validator.removeAll("NOT");
        validator.removeAll("heRE");

        Assert.assertEquals(tags, validator.getTags());
	}

    /**
     * Add your own test to test your removeAll method
     * Add your own comment here:
     */
    @Test
    public void myRemoveAllTest4(){

        HtmlTags[] tagsArr = {new HtmlTags("Hello"), new HtmlTags("There")};
        List<HtmlTags> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTags("Bobby"));

        validator.removeAll("Bobby");

        Assert.assertEquals(tags, validator.getTags());
    }
    @Test
    public void addTagTest() {
        HtmlTags[] tagsArr = {new HtmlTags("Hello"), new HtmlTags("There")};
        List<HtmlTags> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();

        tags.forEach(validator::addTag);

        Assert.assertEquals(tags, validator.getTags());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullTagTest() {
        HtmlValidator validator = new HtmlValidator();
        validator.addTag(null);
    }

    @Test
    public void removeAllTest() {
        HtmlTags[] tagsArr = {new HtmlTags("Hello"), new HtmlTags("There")};
        List<HtmlTags> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTags("General Kenobi"));

        validator.removeAll("General Kenobi");

        Assert.assertEquals(tags, validator.getTags());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAllNullTest() {
        HtmlValidator validator = new HtmlValidator();

        validator.removeAll(null);
    }
}
