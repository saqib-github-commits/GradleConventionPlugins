package com.example.gradleplugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose (
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("kotlinCompilerExtension").get().toString()
        }
    }

    dependencies {
        val composeBom = libs.findLibrary("compose.bom").get()
        add("implementation", platform(composeBom))
        add("implementation", libs.findLibrary("compose.ui").get())
        add("implementation", libs.findLibrary("compose.ui.tooling.preview").get())
        add("implementation", libs.findLibrary("compose.material3").get())
        add("debugImplementation", libs.findLibrary("compose.ui.tooling").get())
    }

}