//default package

// Java 2 Standard Edition
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

// Third-party packages
import org.jbpt.persist.MySQLConnection;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

// Local classes
import org.pql.bot.AbstractPQLBot.NameInUseException;
import org.pql.bot.PQLBot;
import org.pql.core.PQLBasicPredicatesMC;
import org.pql.index.IndexType;
import org.pql.index.PQLIndexMySQL;
import org.pql.ini.PQLIniFile;
import org.pql.label.ILabelManager;
import org.pql.label.LabelManagerLevenshtein;
import org.pql.label.LabelManagerLuceneVSM;
import org.pql.label.LabelManagerThemisVSM;
import org.pql.mc.LoLA2ModelChecker;

public class PQLBotTest {

    private PQLBot bot;

    /** Create a test instance of {@link PQLBot}. */
    @Before public void createBot() throws ClassNotFoundException, IOException, NameInUseException, SQLException {
        PQLIniFile iniFile = new PQLIniFile();
        assertTrue("Unable to read local configuration from PQL.ini file", iniFile.load());

        Connection connection = (new MySQLConnection(iniFile.getMySQLURL(),iniFile.getMySQLUser(),iniFile.getMySQLPassword())).getConnection();
        int sleepTime = iniFile.getDefaultBotSleepTime();
        int indexTime = iniFile.getDefaultBotMaxIndexTime();
        String botName = UUID.randomUUID().toString();

        ILabelManager labelMngr = null;
        switch (iniFile.getLabelManagerType()) {
            case THEMIS_VSM:
                labelMngr = new LabelManagerThemisVSM(connection,
                iniFile.getPostgreSQLHost(),iniFile.getPostgreSQLName(),iniFile.getPostgreSQLUser(),iniFile.getPostgreSQLPassword(),
                iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
                break;
            case LUCENE:
                labelMngr = new LabelManagerLuceneVSM(connection,
                iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds(),iniFile.getLabelSimilaritySeacrhConfiguration());
                break;
            default:
                labelMngr = new LabelManagerLevenshtein(connection,
                iniFile.getDefaultLabelSimilarityThreshold(),iniFile.getIndexedLabelSimilarityThresholds());
                break;
        }

        LoLA2ModelChecker    mc    = new LoLA2ModelChecker(iniFile.getLoLA2Path());
        PQLBasicPredicatesMC bp    = new PQLBasicPredicatesMC(mc);
        PQLIndexMySQL        index = new PQLIndexMySQL(connection,bp,labelMngr,mc,
                                                       iniFile.getDefaultLabelSimilarityThreshold(),
                                                       iniFile.getIndexedLabelSimilarityThresholds(),
                                                       iniFile.getIndexType(),
                                                       iniFile.getDefaultBotMaxIndexTime(),
                                                       iniFile.getDefaultBotSleepTime());
        bot = new PQLBot(connection, botName, index, mc, IndexType.PREDICATES, indexTime, sleepTime);
    }

    /** Test the {@link IPQLBot#terminate} method. */
    @Test public void testTerminate() throws Exception {
        bot.start();
        //Thread.currentThread().sleep(500);  /* Give the bot half a second to run */
        bot.terminate();
        //Thread.currentThread().sleep(2500);  /* Give the bot half a second to terminate */
        //assertTrue(!bot.isAlive());
    }
}
