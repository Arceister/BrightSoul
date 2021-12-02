<?xml version="1.0" encoding="utf-8"?>
<!--
**
** Copyright 2017, The Android Open Source Project
**
** Licensed under the Apache License, Version 2.0 (the "License")
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**     http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/
-->
<device name="Android">
    <!-- All values are in mAh except as noted.
         This file is for PowerProfileTest.java. Changes must be synced between these two. Since
         power_profile.xml may be overridden by actual device's power_profile.xml at compile time,
         this test config ensures we have something constant to test against. Values below are
         sample values, not meant to reflect any real device.
    -->

    <!-- This is the battery capacity in mAh -->
    <item name="battery.capacity">3000</item>

    <!-- Number of cores each CPU cluster contains -->
    <array name="cpu.clusters.cores">
        <value>4</value> <!-- Cluster 0 has 4 cores (cpu0, cpu1, cpu2, cpu3) -->
        <value>4</value> <!-- Cluster 1 has 4 cores (cpu4, cpu5, cpu5, cpu7) -->
    </array>

    <!-- Power consumption when CPU is suspended -->
    <item name="cpu.suspend">5</item>
    <!-- Additional power consumption when CPU is in a kernel idle loop -->
    <item name="cpu.idle">1.11</item>
    <!-- Additional power consumption by CPU excluding cluster and core when  running -->
    <item name="cpu.active">2.55</item>

    <!-- Additional power consumption by CPU cluster0 itself when running excluding cores in it -->
    <item name="cpu.cluster_power.cluster0">2.11</item>
    <!-- Additional power consumption by CPU cluster1 itself when running excluding cores in it -->
    <item name="cpu.cluster_power.cluster1">2.22</item>

    <!-- Different CPU speeds as reported in
         /sys/devices/system/cpu/cpu0/cpufreq/stats/scaling_available_frequencies -->
    <array name="cpu.core_speeds.cluster0">
        <value>300000</value> <!-- 300 MHz CPU speed -->
        <value>1000000</value> <!-- 1000 MHz CPU speed -->
        <value>2000000</value> <!-- 2000 MHz CPU speed -->
    </array>
    <!-- Different CPU speeds as reported in
         /sys/devices/system/cpu/cpu4/cpufreq/stats/scaling_available_frequencies -->
    <array name="cpu.core_speeds.cluster1">
        <value>300000</value> <!-- 300 MHz CPU speed -->
        <value>1000000</value> <!-- 1000 MHz CPU speed -->