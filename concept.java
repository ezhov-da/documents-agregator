import java.util.Arrays;

class Test {
    {
        Iterator<Document> iterator = new Documents().all();
        Document document = new Documents(new Document()).create();
        Document document = new Documents(new Document()).edit();

        //----------------------------------------------------------------------

        new Document(new DocumentData()).createData();
        new Document(new DocumentData()).editData();

        Fields fields = new Document().fields();
        new Document(new Fields()).createFields();
        new Document(new Fields()).editFields();

        //----------------------------------------------------------------------

        Iterator<Field> itFields = new Fields().all();
        Iterator<Field> itFields field = new Fields(new Field(), Arrays.asList(new Field())).addField();
        Iterator<Field> itFields field = new Fields(new Field(), Arrays.asList(new Field())).editField();

        //----------------------------------------------------------------------

        new Field().name();
        new Field().columnName();
        new Field().length();
        new Field().acive();
    }
}
