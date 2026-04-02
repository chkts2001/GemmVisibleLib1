plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    //id("maven-publish")
}

android {
    namespace = "com.gemminiii.library"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

//afterEvaluate {
//    publishing {
//        publications {
//            create<MavenPublication>("release") {
//                from(components["release"])
//                groupId = "com.github.chkts2001"
//                artifactId = "gemm_visible_lib"
//                version = "0.1.2"
//            }
//        }
//    }
//    tasks.named("publishReleasePublicationToMavenLocal") {
//        dependsOn(tasks.named("bundleReleaseAar"))
//    }
//}
