<?php


namespace Disk;


use Illuminate\Support\ServiceProvider;

class DiskProvider extends ServiceProvider
{
    public function register()
    {
        $this->app->singleton('Disk', function () {
            return $this->app->make(Disk::class);
        });
    }
}
