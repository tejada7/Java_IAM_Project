/**
 * TestWhereClauseBuilder.java
 */
package com.ftm.specialization.iamcore.tests.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import com.ftm.iamcore.datamodel.Identity;
import com.ftm.iamcore.utils.WhereClauseBuilder;

import org.junit.Assert;

/**
 * Test the creation of where clauses given an object
 * @author Favio
 *
 */
public class TestWhereClauseBuilder {

	@Test
	public void testWhereClauseCreation() throws ParseException {
		Identity identity = new Identity("Favio", "Tejada", "favio@hotmail.com"
				, new SimpleDateFormat("dd/MM/yyyy").parse("15/03/1992"));
		String expectedOutput = " where email = 'favio@hotmail.com' "
				+ "or birthDate = '15/03/1992'"
				+ " or displayName = 'Favio'";
		//Using WhereClauseBuilder to generate the sentence
		String outPut = WhereClauseBuilder.getClause(identity);
		Assert.assertEquals(expectedOutput, outPut);
	}
}