import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("Kemini - The Gemini AI KMM library")
            description.set("Use Gemini AI in your KMM projects")
            url.set("https://github.com/prasannajeet/kemini-ai")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("Prasan")
                    name.set("Prasan Pani")
                    organization.set("Prasan")
                    organizationUrl.set("https://github.com/prasannajeet")
                }
            }
            scm {
                url.set("https://github.com/prasannajeet/kemini-ai")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
