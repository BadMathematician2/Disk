<?php


namespace App\Packages\Disk;


use Symfony\Component\Console\Command\Command;
use Symfony\Component\Process\Process;

class Disk
{
    protected $path_to_files = __DIR__.'/resources/files/';

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
     * i -- it is id
     * x -- it is longitude
     * y -- it is latitude
     *
     * @param float $xCenter
     * @param float $yCenter
     * @param float $radius
     * @return false|string
     */
    public final function data($xCenter,$yCenter,$radius)
    {
        $result = array(['i' => 0, 'x' => 0, 'y' => 0]);
        $names = file($this->path_to_files.'names.txt');

        $first = $this->getBorder($names,$xCenter - $radius);
        $last = $this->getBorder($names, $xCenter + $radius, $first);

        for ($i = $first; $i <= $last; $i++) {
            $points = file($this->getFileName($names[$i]));
            foreach ($points as $point) {
                $arr = explode(";", $point, 3);
                if($this->checkInDisc($xCenter,$yCenter,$radius,$arr)) {
                    array_push($result, ['i' => $arr[0], 'x' => $arr[1], 'y' => (float)$arr[2]]);
                }
            }
        }

        return json_encode($result);
    }

    /**
     * @param float $xCenter
     * @param float $yCenter
     * @param float $radius
     * @param $arr
     * @return bool
     */
    private function checkInDisc($xCenter,$yCenter,$radius,$arr)
    {

         return ((float)$arr[1] - $xCenter) ** 2 + ((float)$arr[2] - $yCenter) ** 2 <= $radius ** 2;
    }
    /**
     * @return string
     */
    private function pathToJar()
    {
        return __DIR__.'/bin/mavenPoints.jar';
    }

    /**
     * @param string $name
     * @return string
     */
    private function getFileName($name)
    {
        return $this->path_to_files . substr($name, 0, strlen($name) - 1) . '.txt';
    }

    /**
     * @param string[] $names
     * @param float $distance
     * @param int $start
     * @return int
     */
    private function getBorder($names,$distance, $start = 0)
    {
        $result = $start;
        for ($i = 0; $i < sizeof($names); $i++) {
            if ($distance <= (float)$names[$i]) {
                $result = $i;
                break;
            }
        }

        return $result;
    }
}
