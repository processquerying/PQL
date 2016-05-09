package org.jbpt.persist;

import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit tests for {@link URIHelper}.
 * 
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class URIHelperTest {

    /** Testing {@link URIHelper#addQueryProperty} method when the <var>uri</var> argument is <code>null</code>. */
    @Test(expected = IllegalArgumentException.class)
    public void testAddQueryProperty_NullURI() throws URISyntaxException {
        URIHelper.addQueryProperty(null, "property", "value");
    }

    /** Testing {@link URIHelper#addQueryProperty} method when the <var>property</var> argument is <code>null</code>. */
    @Test(expected = IllegalArgumentException.class)
    public void testAddQueryProperty_NullProperty() throws URISyntaxException {
        URI uri = new URI("http://example.com/foo");
        URIHelper.addQueryProperty(uri, null, "value");
    }

    /** Testing {@link URIHelper#addQueryProperty} method when the <var>value</var> argument is <code>null</code>. */
    @Test
    public void testAddQueryProperty_NullValue() throws URISyntaxException {
        URI actual = URIHelper.addQueryProperty(new URI("http://example.com/foo"), "property", null);
        URI expected = new URI("http://example.com/foo?property");
	assertEquals(expected, actual);
    }

    /** Testing {@link URIHelper#addQueryProperty} method when the <var>uri</var> has no existing query part. */
    @Test
    public void testAddQueryProperty_NoExistingQuery() throws URISyntaxException {
        URI actual = URIHelper.addQueryProperty(new URI("http://example.com/foo"), "property", "value");
        URI expected = new URI("http://example.com/foo?property=value");
	assertEquals(expected, actual);
    }

    /** Testing {@link URIHelper#addQueryProperty} method when the <var>uri</var> has an existing query part. */
    @Test
    public void testAddQueryProperty_ExistingQuery() throws URISyntaxException {
        URI actual = URIHelper.addQueryProperty(new URI("http://example.com/foo?existingProperty=otherValue"), "property", "value");
        URI expected = new URI("http://example.com/foo?existingProperty=otherValue&property=value");
	assertEquals(expected, actual);
    }
}
