package com.kenzan.karyon.rxnetty;

import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.Karyon;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrapModule;

public class KaryonRxNettyExample {

    public static void main(String[] args) {
        Karyon.forRequestHandler(8080,
                new ExampleRouteInterceptor(),
                new ArchaiusBootstrapModule("hello-karyon-rxnetty"),
                Karyon.toBootstrapModule(KaryonWebAdminModule.class),
                ShutdownModule.asBootstrapModule()
                )
        .startAndWaitTillShutdown();
    }
}
