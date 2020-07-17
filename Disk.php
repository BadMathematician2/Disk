<?php


namespace App\Packages\Disk;


use Symfony\Component\Console\Command\Command;
use Symfony\Component\Process\Process;

class Disk
{
    /**
     * @param string $file_name
     * @return string|null
     */
    public final function update($file_name)
    {
        $command = new Process(['java', '-jar', $this->pathToJar(), '--update='.$file_name , __DIR__. DIRECTORY_SEPARATOR . 'resources']);
        $command->run();
        return $command->getOutput();
    }


    /**
     * @param float $xCenter
     * @param float $yCenter
     * @param float $radius
     * @return false|string
     */
    public final function data($xCenter,$yCenter,$radius)
    {
        $result = array(['id' => 0, 'long' => 0, 'lati' => 0]);
        $names = file(__DIR__.'/resources/files/names.txt');
        $first = 0;
        $last = sizeof($names) - 2;
        for ($i = 0; $i < sizeof($names); $i++) {
            if ($xCenter - $radius <= (float)$names[$i]) {
                $first = $i;
                break;
            }
        }
        for ($i = $first; $i < sizeof($names); $i++) {
            if ($xCenter + $radius <= (float)$names[$i]) {
                $last = $i;
                break;
            }
        }
        for ($i = $first; $i <= $last; $i++) {
            $points = file(__DIR__.'/resources/files/' . substr($names[$i], 0, strlen($names[$i]) - 1) . '.txt');
            foreach ($points as $point) {
                $arr = explode(";", $point, 3);
                if (((float)$arr[1] - $xCenter) ** 2 + ((float)$arr[2] - $yCenter) ** 2 <= $radius ** 2) {
                    array_push($result, ['id' => $arr[0], 'long' => $arr[1], 'lati' => (float)$arr[2]]);
                }
            }
        }
        return json_encode($result);

    }

    /**
     * @return string
     */
    private function pathToJar()
    {
        return __DIR__.'/bin/mavenPoints.jar';
    }
}
