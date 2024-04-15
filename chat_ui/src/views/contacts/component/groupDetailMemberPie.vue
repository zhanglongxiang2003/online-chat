<script setup>
import * as echarts from 'echarts';
import { onMounted } from 'vue';


onMounted(() => {
    //成员分布 - Echarts图标
    let myChartData = []
    const userAgent = navigator.userAgent
    console.log(userAgent);
    if (userAgent.match(/Mobile/i) || userAgent.match(/iPhone/i)) {
        myChartData = [
            { center: ['12%', '50%'], radius: '60%', value: 8, name: '活跃-4人', offsetY: ['5%', '150%'] },
            { center: ['37%', '50%'], radius: '60%', value: 25, name: '万州-8人', offsetY: ['5%', '150%'] },
            { center: ['63%', '50%'], radius: '60%', value: 38, name: '00后-13人', offsetY: ['5%', '150%'] },
            { center: ['88%', '50%'], radius: '60%', value: 12, name: '男-5人', offsetY: ['5%', '150%'] },
        ]
    } else if (userAgent.match(/Windows/i)) {
        myChartData = [
            { center: ['10%', '50%'], radius: '70%', value: 18, name: '活跃-4人', offsetY: ['5%', '130%'] },
            { center: ['36%', '50%'], radius: '70%', value: 18, name: '万州-8人', offsetY: ['5%', '130%'] },
            { center: ['64%', '50%'], radius: '70%', value: 18, name: '00后-13人', offsetY: ['5%', '130%'] },
            { center: ['90%', '50%'], radius: '70%', value: 18, name: '男-5人', offsetY: ['5%', '130%'] },
        ]
    } else {
        myChartData = [
            { center: ['10%', '50%'], radius: '70%', value: 18, name: '活跃-4人', offsetY: ['5%', '130%'] },
            { center: ['36%', '50%'], radius: '70%', value: 18, name: '万州-8人', offsetY: ['5%', '130%'] },
            { center: ['64%', '50%'], radius: '70%', value: 18, name: '00后-13人', offsetY: ['5%', '130%'] },
            { center: ['90%', '50%'], radius: '70%', value: 18, name: '男-5人', offsetY: ['5%', '130%'] },
        ]
    }
    const myChart = echarts.init(document.getElementById('group-member-pie-box'))
    myChart.setOption({
        series: [
            {
                center: myChartData[0].center,
                type: 'gauge',
                startAngle: 90,
                endAngle: -270,
                pointer: {
                    show: false
                },
                radius: myChartData[0].radius,
                progress: {
                    show: true,
                    overlap: false,
                    roundCap: true,
                    clip: false,
                    itemStyle: {
                        borderWidth: 0,
                        borderColor: '#464646',
                        color: '#A06AF0'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: [[1, '#eee']]
                    }
                },
                splitLine: {
                    show: false,
                    distance: 0,
                    length: 10
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false,
                    distance: 50
                },
                data: [
                    {
                        value: myChartData[0].value,
                        name: myChartData[0].name,
                        title: {
                            show: true,
                            offsetCenter: myChartData[0].offsetY,
                            fontSize: 12
                        },
                        detail: {
                            valueAnimation: true,
                            offsetCenter: ['0%', '0%']
                        }
                    }
                ],
                title: {
                    fontSize: 14
                },
                detail: {
                    width: 50,
                    height: 14,
                    fontSize: 16,
                    color: '#7F7F7F',
                    formatter: '{value}%'
                }
            },
            {
                center: myChartData[1].center,
                type: 'gauge',
                startAngle: 90,
                endAngle: -270,
                pointer: {
                    show: false
                },
                radius: myChartData[1].radius,
                progress: {
                    show: true,
                    overlap: false,
                    roundCap: true,
                    clip: false,
                    itemStyle: {
                        borderWidth: 0,
                        borderColor: '#464646'
                    }
                },

                axisLine: {
                    lineStyle: {
                        color: [[1, '#eee']]
                    }
                },
                splitLine: {
                    show: false,
                    distance: 0,
                    length: 10
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false,
                    distance: 50
                },
                data: [
                    {
                        value: myChartData[1].value,
                        name: myChartData[1].name,
                        title: {
                            show: true,
                            offsetCenter: myChartData[0].offsetY,
                            fontSize: 12
                        },
                        detail: {
                            valueAnimation: true,
                            offsetCenter: ['0%', '0%']
                        }
                    }
                ],
                title: {
                    fontSize: 14
                },
                detail: {
                    width: 50,
                    height: 14,
                    fontSize: 16,
                    color: '#7F7F7F',
                    formatter: '{value}%'
                }
            },
            {
                center: myChartData[2].center,
                type: 'gauge',
                startAngle: 90,
                endAngle: -270,
                pointer: {
                    show: false
                },
                radius: myChartData[2].radius,
                progress: {
                    show: true,
                    overlap: false,
                    roundCap: true,
                    clip: false,
                    itemStyle: {
                        borderWidth: 0,
                        borderColor: '#464646'
                    }
                },

                axisLine: {
                    lineStyle: {
                        color: [[1, '#eee']]
                    }
                },
                splitLine: {
                    show: false,
                    distance: 0,
                    length: 10
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false,
                    distance: 50
                },
                data: [
                    {
                        value: myChartData[2].value,
                        name: myChartData[2].name,
                        title: {
                            show: true,
                            offsetCenter: myChartData[0].offsetY,
                            fontSize: 12
                        },
                        detail: {
                            valueAnimation: true,
                            offsetCenter: ['0%', '0%']
                        }
                    }
                ],
                title: {
                    fontSize: 14
                },
                detail: {
                    width: 50,
                    height: 14,
                    fontSize: 16,
                    color: '#7F7F7F',
                    formatter: '{value}%'
                }
            },
            {
                center: myChartData[3].center,
                type: 'gauge',
                startAngle: 90,
                endAngle: -270,
                pointer: {
                    show: false
                },
                radius: myChartData[3].radius,
                progress: {
                    show: true,
                    overlap: false,
                    roundCap: true,
                    clip: false,
                    itemStyle: {
                        borderWidth: 0,
                        borderColor: '#464646'
                    }
                },

                axisLine: {
                    lineStyle: {
                        color: [[1, '#eee']]
                    }
                },
                splitLine: {
                    show: false,
                    distance: 0,
                    length: 10
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false,
                    distance: 50
                },
                data: [
                    {
                        value: myChartData[3].value,
                        name: myChartData[3].name,
                        title: {
                            show: true,
                            offsetCenter: myChartData[0].offsetY,
                            fontSize: 12
                        },
                        detail: {
                            valueAnimation: true,
                            offsetCenter: ['0%', '0%']
                        }
                    }
                ],
                title: {
                    fontSize: 14
                },
                detail: {
                    width: 50,
                    height: 14,
                    fontSize: 16,
                    color: '#7F7F7F',
                    formatter: '{value}%'
                }
            }
        ]
    })
    window.addEventListener('resize', myChart.resize)
})  
</script>
<template>
    <div class="member-pie-box" id="group-member-pie-box"></div>
</template>