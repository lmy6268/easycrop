plugins {
    kotlin("android")
    id ("com.android.library")
}

val composeBomVersion : String by project
val composeCompilerVersion : String by project

android {
    namespace = "com.mr0xf00.easycrop"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        lint.targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    implementation ("androidx.core:core-ktx:1.9.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.activity:activity-compose:1.6.1")
    implementation ("androidx.compose.material:material")
    implementation ("androidx.compose.ui:ui")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}
