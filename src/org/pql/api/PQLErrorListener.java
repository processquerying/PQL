package org.pql.api;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * Artem Polyvyanyy
 */
public class PQLErrorListener extends BaseErrorListener {
	private List<String> errors = new ArrayList<String>();

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
		
		this.errors.add(String.format("Line %d at position %d:\t%s", line, charPositionInLine, msg));
	}
	
	public List<String> getErrorMessages() {
		return this.errors;
	}
	
	public int getNumberOfErrors() {
		return this.errors.size();
	}

}
