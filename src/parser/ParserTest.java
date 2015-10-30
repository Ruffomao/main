/*
 * @author: Jeston Teo
 */

package parser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import logic.AddTask;
import logic.DeleteTask;
import logic.MarkDoneTask;
import logic.InvalidTask;
import logic.SearchTask;
import logic.ShowTask;
import logic.UpdateTask;

public class ParserTest {

	ArrayList<String> expectedDate = new ArrayList<String>();
	ArrayList<String> expectedTime = new ArrayList<String>();

	@Test
	public void testSetCommandWithTimeOnly() {
		String test = "add CS2103T lecture from 2pm to 4:00pm";
		assertTrue(Parser.setCommand(test) instanceof AddTask);
		AddTask add = (AddTask) Parser.setCommand(test);

		String expected = "CS2103T lecture";
		expectedDate = add(DateTimeParser.getDate(0), DateTimeParser.getDate(0));
		expectedTime = add("1400", "1600");

		assertEquals(expected, add.getEventTask());
		assertEquals(expectedDate, add.getDate());
		assertEquals(expectedTime, add.getTime());
	}

	@Test
	public void testSetCommandWithValidDateOnly() {
		String test = "new reservist 9/5/16 to 30/5/16";
		assertTrue(Parser.setCommand(test) instanceof AddTask);
		AddTask add = (AddTask) Parser.setCommand(test);

		String expected = "reservist";
		expectedDate = add("09/05/16", "30/05/16");
		expectedTime = add("", "");

		assertEquals(expected, add.getEventTask());
		assertEquals(expectedDate, add.getDate());
		assertEquals(expectedTime, add.getTime());
	}

	@Test
	public void testSetCommandWithInvalidDateOnly() {
		String test = "add reservist 9/5/15 to 30/5/15";
		assertTrue(Parser.setCommand(test) instanceof InvalidTask);
		// AddTask add = (AddTask) Parser.setCommand(test);
		//
		// String expected = "reservist";
		// expectedDate = add("09/05/15", "30/05/15");
		// expectedTime = add("", "");
		//
		// assertEquals(expected, add.getEventTask());
		// assertEquals(expectedDate, add.getDate());
		// assertEquals(expectedTime, add.getTime());
	}

	@Test
	public void testSetCommandWithInvalidTimeInputs() {
		String test = "new event from 11:00 12:00 to 12:00";
		assertTrue(Parser.setCommand(test) instanceof InvalidTask);
	}

	@Test
	public void testForSearchAction() {
		String test = "seArch this event";
		assertTrue(Parser.setCommand(test) instanceof SearchTask);
		SearchTask search = (SearchTask) Parser.setCommand(test);

		String expected = "this event";
		assertEquals(expected, search.getEventTask());
	}

	@Test
	public void testForFailedSearchActionNoEventTaskArg() {
		String test = "search ";
		assertTrue(Parser.setCommand(test) instanceof InvalidTask);
	}

	@Test
	public void testUpdateAction() {
		String test = "update 1 new event name from 10/10/15 1100 to 11/10/15 12.00";
		assertTrue(Parser.setCommand(test) instanceof UpdateTask);
		UpdateTask update = (UpdateTask) Parser.setCommand(test);

		String expectedEvent = "new event name";
		int expectedIndex = 1;
		assertEquals(expectedEvent, update.getEventTask());
		expectedDate.add("10/10/15");
		expectedDate.add("11/10/15");
		expectedTime.add("1100");
		expectedTime.add("1200");
		assertEquals(expectedDate, update.getDate());
		assertEquals(expectedTime, update.getTime());
		assertEquals(expectedIndex, update.getIndex());
	}

	@Test
	public void testDoneAction() {
		String test = "done 2";
		assertTrue(Parser.setCommand(test) instanceof MarkDoneTask);
		MarkDoneTask done = (MarkDoneTask) Parser.setCommand(test);

		int expected = 2;
		assertEquals(expected, done.getIndex());
	}

	@Test
	public void testFailedDoneAction() {
		String test = "done";
		assertTrue(Parser.setCommand(test) instanceof InvalidTask);
	}

	@Test
	public void testDeleteAction() {
		String test = "deL 2";
		assertTrue(Parser.setCommand(test) instanceof DeleteTask);
		DeleteTask delete = (DeleteTask) Parser.setCommand(test);

		int expected = 2;
		assertEquals(expected, delete.getIndex());
	}

	@Test
	public void testFailedDeleteAction() {
		String test = "delete ";
		assertTrue(Parser.setCommand(test) instanceof InvalidTask);
	}

	@Test
	public void testShowAction() {
		String test = "display today";
		assertTrue(Parser.setCommand(test) instanceof ShowTask);
		ShowTask show = (ShowTask) Parser.setCommand(test);

		String expected = DateTimeParser.getDate(0);
		assertEquals(expected, show.getDate());
	}

	@Test
	public void testEmptyShowAction() {
		String test = "show";
		assertTrue(Parser.setCommand(test) instanceof ShowTask);
		
		ShowTask show = (ShowTask) Parser.setCommand(test);
		
		String expected = "";
		assertEquals(expected, show.getDate());
	}

	@Test
	public void testFailedShowActionTooManyInputs() {
		String test = "show 10/11/15 11/11/15";
		assertTrue(Parser.setCommand(test) instanceof InvalidTask);
	}

	// For easier adding to ArrayLists
	private static ArrayList<String> add(String start, String end) {
		ArrayList<String> returnArr = new ArrayList<String>();
		returnArr.add(start);
		returnArr.add(end);
		return returnArr;
	}
}
