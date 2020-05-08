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
import com.zeroc.Ice.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server implementation.
 *
 * @author Diego Urrutia-Astorga.
 */
@SuppressWarnings("UtilityClass")
public final class SystemServer {

    /**
     * The logger.
     */
    private static final Logger log = LoggerFactory.getLogger(SystemServer.class);

    /**
     * Empty constructor.
     */
    private SystemServer() {
        // Nothing here.
    }

    /**
     * @param args to use.
     */
    public static void main(final String[] args) {

        log.debug("Starting the Server ..");

        // The communicator
        try (Communicator communicator = Util.initialize(getInitializationData(args))) {

            // The adapter
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("TheAdapter", "default -p 8080 -z");

            // Inline implementation (Lambda)
            TheSystem theSystem = (clientTime, current) -> System.currentTimeMillis() - clientTime;

            // The unique identity
            Identity identity = Util.stringToIdentity(TheSystem.class.getName());
            log.debug("Using name [{}] and category [{}] as identity.", identity.name, identity.category);

            // Register the API in the framework
            adapter.add(theSystem, identity);
            adapter.activate();

            // .. waiting.
            log.debug("Waiting for connections ..");
            communicator.waitForShutdown();
        }

        log.debug("Server ended!");

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
        properties.setProperty("Ice.Trace.Admin.Properties", "1");
        properties.setProperty("Ice.Trace.Locator", "2");
        properties.setProperty("Ice.Trace.Network", "3");
        properties.setProperty("Ice.Trace.Protocol", "1");
        properties.setProperty("Ice.Trace.Slicing", "1");
        properties.setProperty("Ice.Trace.ThreadPool", "1");
        properties.setProperty("Ice.Compression.Level", "9");
        properties.setProperty("Ice.Plugin.Slf4jLogger.java", "cl.ucn.disc.pdis.fivet.zeroice.Slf4jLoggerPluginFactory");

        InitializationData initializationData = new InitializationData();
        initializationData.properties = properties;

        return initializationData;
    }

}
