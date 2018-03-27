import java.util.Arrays;

class Test {
    {
        Iterator<Document> iterator = new Documents().all();
        Document document = new Documents(new Document()).create();
        Document document = new Documents(new Document()).edit();
        Document document = new Documents(new Document()).delete();
        //----------------------------------------------------------------------

        new Document(new DocumentData()).delete();

        new Document(new DocumentData()).createData();
        new Document(new DocumentData()).editData();
        new Document(new DocumentData()).deleteData();

        Fields fields = new Document().fields();
        //----------------------------------------------------------------------

        Iterator<Field> itFields = new Fields().all();
        Iterator<Field> itFields field = new Fields(new Field(), Arrays.asList(new Field())).addField();
        Iterator<Field> itFields field = new Fields(new Field(), Arrays.asList(new Field())).editField();
        Iterator<Field> itFields field = new Fields(new Field(), Arrays.asList(new Field())).deleteField();

        //----------------------------------------------------------------------

        new Field().name();
        new Field().columnName();
        new Field().length();
        new Field().acive();
    }
}
