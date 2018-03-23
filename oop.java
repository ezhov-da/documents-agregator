// Как видит программист объект:
class Render {
    public String getHtml() {
        return "<html>o, no</html>";
    }

    public String getJson() {
        return "{'text':'o, no'}";
    }
}

// Как видит ООП программист этот же объект:
class BigFunction {
    public String liltleFunctionOne() {
        return "<html>i,m cry</html>";
    }

    public String liltleFunctionTwo() {
        return "{'text':'i,m cry too'}";
    }
}

//Что сделает ООП программист
interface View {
    String asString();
}

class HtmlView implements View {

    @Override
    public String asString() {
        return "<html>o, yes</html>";
    }
}

class JsonView implements View {

    @Override
    public String asString() {
        return "{'text':'o, yes'}";
    }
}
