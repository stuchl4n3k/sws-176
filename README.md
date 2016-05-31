# sws-176

Reproduction of [SWS-176](https://jira.spring.io/browse/SWS-176) PayloadValidatingInterceptor ignores schema list.

## How to reproduce

### 1. Run `mvn clean test` - the test fails:

```
...
Caused by: org.xml.sax.SAXParseException; systemId: file:/sws-176/target/classes/xsd/ExampleWS.xsd; lineNumber: 12; columnNumber: 91; src-resolve: Cannot resolve the name 'dom:ExampleRequestType' to a(n) 'type definition' component.
	at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:203)
	at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.error(ErrorHandlerWrapper.java:134)
	at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:396)
	at com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDHandler.reportSchemaErr(XSDHandler.java:4158)
	at com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDHandler.reportSchemaError(XSDHandler.java:4141)
	at com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDHandler.getGlobalDecl(XSDHandler.java:1737)
	at com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDElementTraverser.traverseNamedElement(XSDElementTraverser.java:405)
	at com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDElementTraverser.traverseLocal(XSDElementTraverser.java:194)
	at com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDHandler.traverseLocalElements(XSDHandler.java:3614)
	at com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDHandler.parseSchema(XSDHandler.java:629)
	at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader.loadSchema(XMLSchemaLoader.java:613)
	at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader.loadGrammar(XMLSchemaLoader.java:572)
	at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader.loadGrammar(XMLSchemaLoader.java:538)
	at com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory.newSchema(XMLSchemaFactory.java:255)
	at org.springframework.xml.validation.SchemaLoaderUtils.loadSchema(SchemaLoaderUtils.java:78)
	at org.springframework.xml.validation.Jaxp13ValidatorFactory.createValidator(Jaxp13ValidatorFactory.java:41)
	... 59 more
```

### 2. Change order of schema list in `springws-endpoint-context.xml` on line 24 like this:

```
<!-- FAILS -->
<value>classpath:xsd/Foo.xsd</value>
<value>classpath:xsd/ExampleWS.xsd</value>
```

```
<!-- WORKS -->
<value>classpath:xsd/ExampleWS.xsd</value>
<value>classpath:xsd/Foo.xsd</value>
```

### 3. Run `mvn clean test` - the test passes.

