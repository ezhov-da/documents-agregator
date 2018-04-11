package ru.ezhov.document.web;

import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;
import ru.ezhov.document.core.source.H2Source;
import ru.ezhov.document.core.source.Source;
import ru.ezhov.document.web.takes.TkIndex;

import javax.sql.DataSource;

public final class App {
    public static void main(final String... args) throws Exception {
        Source<DataSource> source = new H2Source();

        new FtBasic(
                new TkFork(new FkRegex("/", new TkIndex(source))), 8080
        ).start(Exit.NEVER);
    }
}
