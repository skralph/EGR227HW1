/**
 * Add your own comments
 */
import java.util.*;
public class HtmlValidator {

    //indentation string constant
    private static final String INDENT = "    ";

    //HtmlTag object queue
    private Queue<HtmlTags> tagQueue;

    public HtmlValidator() {
        //initializing a queue
        tagQueue = new LinkedList<>();
    }

    /** An empty queue (size 0)
     * is allowed.  The constructors are allowed to construct
     * a queue to store your validator's tags if necessary.
     */
    //receives queue of HtmlTag, initialize with copy of queue
    public HtmlValidator(Queue<HtmlTags> tagQueue) {
        //throw an exception if the initial tag is null
        if (tagQueue == null) {
            throw new IllegalArgumentException("Initial tag must not be null.");
        }
        // initialize your validator with an entirely separate copy of the queue it was passed
        this.tagQueue = new LinkedList<>(tagQueue);
    }

    //receive tag, add to the end of validator queue
    public void addTag(HtmlTags tag) {
        //throw an exception if the input tag is null
        if (tag == null) {
            throw new IllegalArgumentException("Cannot add a null tag");
        }
        //add input tag to end of queue
        tagQueue.add(tag);
    }

    //return validator's queue of HTML tags
    public Queue<HtmlTags> getTags() {
        // return an object copy of tags
        return new LinkedList<>(tagQueue);
    }

    //remove from validator queue any tags that match given element
    public void removeAll(String element) {
        //throw an exception if the element is null
        if (element == null) {
            throw new IllegalArgumentException("Cannot add a null element");
        }

        //create new queue with tags that don't equal the element given
        Queue<HtmlTags> remainingTags = new LinkedList<>();

        //for tags in queue, if tag does not equal element, add to new queue
        for (HtmlTags tag : tagQueue) {
            if (!tag.getElement().equalsIgnoreCase(element)) {
                remainingTags.add(tag);
            }
        }
        //update validator queue with remaining tags
        tagQueue = remainingTags;
    }

    /**
     * Print formatted HTML based on the content of the HtmlValidator. The
     * output is indented according to tag depth.
     *
     * Unexpected and unclosed tags are printed as error messages at 0 depth
     *
     //Display each tag on its own line.
     Every opening tag that
     * requires a closing tag increases the level of indentation of following
     * tags by four spaces until its closing tag is reached.
     *
     * Examine each tag from the queue, and if it is an opening
     * tag that requires a closing tag, push it onto a stack and
     * increase indentation.  If it is a closing tag, compare it
     * to the tag on top of the stack.  If the two tags match, pop
     * the top tag of the stack and decrease indentation.  If they
     * don't match, it is an error.
     * Any tags remaining on the stack at the end are errors.
     */

    //print an indented text representation of the HTML tags in your queue

    // when you see an opening tag that is not self-closing,
    // you should push it onto a stack and increase your indentation

    // When you see a closing tag, you should
    // pop the top element from the stack and decrease your indentation


    //Method to validate the tags in the queue
    public void validate() {
        //initialize stack of open tags//
        Stack<HtmlTags> openTags = new Stack<>();

        //loop through tags in queue//
        for(int i = 0; i < tagQueue.size(); i++) {
            //Remove the next tag from the queue
            HtmlTags tag = tagQueue.remove();
            //Add the tag back to the end of the queue
            tagQueue.add(tag);
            //Check if the tag is self-closing
            if (tag.isSelfClosing()) {
                if (!tag.isOpenTag()) {
                    System.out.println("ERROR unexpected tag: " + tag.toString());
                }
                else {
                    //Print the tag with indentation based on the number of open tags
                    printWithIndentation(tag, openTags.size());
                }
                //Check if the tag is an open tag
            } else if (tag.isOpenTag()) {
                printWithIndentation(tag, openTags.size());
                openTags.push(tag);
            } else if (!openTags.isEmpty() && tag.matches(openTags.peek())) { // By exhaustion, the tag must be a closing tag
                // Closing tag should be at same depth as opening, so we pop before printing
                openTags.pop();
                printWithIndentation(tag, openTags.size());
            } else {
                System.out.println("ERROR unexpected tag: " + tag.toString());
            }
        }
        // Deal with unclosed tags
        while (!openTags.isEmpty()) {
            HtmlTags tag = openTags.pop();
            System.out.println("ERROR unclosed tag: " + tag.toString());
        }
    }

    // Helper function to make printing at given indentation more convenient
    //Method to print a tag with appropriate indentation
    private static void printWithIndentation(HtmlTags tag, int indentationLevel) {
        //StringBuilder to create the indentation string
        StringBuilder sb = new StringBuilder();
        //Loop to add the indentation marker to the string for the appropriate indentation level
        for (int i = 0; i < indentationLevel; i++) { //sb.append(INDENTATION_MARKER.repeat(Math.max(0, indentationLevel)));
            sb.append(INDENT);
        }
        sb.append(tag.toString());
        //Print the final string
        System.out.println(sb.toString()); //System.out.println(sb);
    }
}

