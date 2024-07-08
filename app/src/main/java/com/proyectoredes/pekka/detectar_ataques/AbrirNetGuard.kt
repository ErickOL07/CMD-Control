package com.proyectoredes.pekka.detectar_ataques

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import java.io.File

class AbrirNetGuard(private val context: Context) {

    private val nombrePaquete = "eu.faircode.netguard" // Nombre del paquete de la aplicación NetGuard
    private val rutaApk = "${Environment.DIRECTORY_DOWNLOADS}/NetGuard/netguard.apk" // Ruta al archivo APK de NetGuard
    private val urlApk = "https://github.com/M66B/NetGuard/releases/download/2.329/NetGuard-v2.329-release.apk" // URL del archivo APK
    private val urlPlayStore = "https://play.google.com/store/apps/details?id=eu.faircode.netguard" // URL de la Play Store

    @RequiresApi(Build.VERSION_CODES.O)
    fun abrirApp() {
        if (estaInstalada(nombrePaquete)) {
            val launchIntent: Intent? = context.packageManager.getLaunchIntentForPackage(nombrePaquete)
            if (launchIntent != null) {
                startActivity(context, launchIntent, null)
            } else {
                println("La aplicación está instalada, pero no se puede abrir")
            }
        } else if (apkExiste(rutaApk)) {
            redirigirPlayStore()
        } else {
            descargarApk(urlApk)
        }
    }

    private fun estaInstalada(nombrePaquete: String): Boolean {
        return try {
            context.packageManager.getPackageInfo(nombrePaquete, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun apkExiste(rutaApk: String): Boolean {
        val apk = File(rutaApk)
        return apk.exists()
    }

    private fun redirigirPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlPlayStore))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun descargarApk(url: String) {
        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setTitle("Descargando NetGuard")
            setDescription("Descargando el archivo APK de NetGuard")
            setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "NetGuard/netguard.apk")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        }

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = downloadManager.enqueue(request)

        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
    }

    private fun instalarApk(rutaApk: String) {
        val apk = File(rutaApk)
        if (apk.exists()) {
            val apkUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", apk)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(intent)
        } else {
            println("El archivo APK no se encuentra en la ruta especificada")
        }
    }
}
