package com.webers.applock.service.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.webers.applock.service.ServiceStarter

class ServiceCheckerWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        ServiceStarter.startService(applicationContext)
        return Result.success()
    }
}