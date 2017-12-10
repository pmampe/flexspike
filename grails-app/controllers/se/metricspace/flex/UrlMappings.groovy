package se.metricspace.flex

class UrlMappings {

    static mappings = {
        "/admin/userOverview/$uid"(controller: "admin", parseRequest: true) {
            action = 'userOverview'
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'dashboard', action: 'index')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
