/*
 * Copyright (c) 2020 Charlie Condorcet Engineer Student.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <https://www.gnu.org/licenses/>.
 */

package cl.ucn.disc.pdis.fivet;

import cl.ucn.disc.pdis.fivet.zeroice.model.TheSystem;
import cl.ucn.disc.pdis.fivet.zeroice.model.TheSystemPrx;
import com.zeroc.Ice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementacion del cliente.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings({"UtilityClass", "LawOfDemeter"})
public final class SystemClient {

    /**
     * The logger.
     */
    private static final Logger log = LoggerFactory.getLogger(SystemClient.class);

    /**
     * Empty constructor.
     */
    private SystemClient() {
        // Nothing here
    }

    /**
     * The main file
     *
     * @param args to use.
     */
    public static void main(String[] args) {

        log.debug("Starting the Client ..");

        try (Communicator communicator = Util.initialize(getInitializationData(args))) {

            // Running in port 8080
            ObjectPrx theProxy = communicator.stringToProxy(TheSystem.class.getName() + ":default -p 8080 -z");

            TheSystemPrx theSystem = TheSystemPrx.checkedCast(theProxy);

            if (theSystem == null) {
                throw new IllegalStateException("Invalid TheSystem! (wrong proxy?)");
            }

            long delay = theSystem.getDelay(System.currentTimeMillis());
            log.debug("Delay: {}ms.", delay);

            /*
            EnginePrx engine = EnginePrx.checkedCast(proxy);

            */

        }

        log.debug("Done.");
    }

    /**
     * @param args to use as source.
     * @return the {@link InitializationData}.
     */
    private static InitializationData getInitializationData(String[] args) {

        // Properties
        final Properties properties = Util.createProperties(args);
        properties.setProperty("Ice.Package.model", "cl.ucn.disc.pdis.fivet.zeroice");

        // https://doc.zeroc.com/ice/latest/property-reference/ice-trace
        // properties.setProperty("Ice.Trace.Admin.Properties", "1");
        // properties.setProperty("Ice.Trace.Locator", "2");
        // properties.setProperty("Ice.Trace.Network", "3");
        // properties.setProperty("Ice.Trace.Protocol", "1");
        // properties.setProperty("Ice.Trace.Slicing", "1");
        // properties.setProperty("Ice.Trace.ThreadPool", "1");
        // properties.setProperty("Ice.Compression.Level", "9");
        properties.setProperty("Ice.Plugin.Slf4jLogger.java", "cl.ucn.disc.pdis.fivet.zeroice.Slf4jLoggerPluginFactory");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }

}
