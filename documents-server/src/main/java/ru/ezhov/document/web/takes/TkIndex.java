package ru.ezhov.document.web.takes;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.rs.RsVelocity;
import ru.ezhov.document.core.source.Source;

import javax.sql.DataSource;

public class TkIndex implements Take {

    private Source<DataSource> source;

    public TkIndex(Source<DataSource> source) {
        this.source = source;
    }

    @Override
    public Response act(final Request req) {
        return new RsVelocity(
                getClass().getResourceAsStream("/index.html"), new RsVelocity.Pair("", "")
        );
    }
}
