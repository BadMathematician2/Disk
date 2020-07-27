<?php


namespace Disk;


use Illuminate\Support\Facades\Facade;

class DiskFacade extends Facade
{
    protected static function getFacadeAccessor()
    {
        return 'Disk';
    }
}
