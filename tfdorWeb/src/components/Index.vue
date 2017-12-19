<template>
	<div>
		<mt-swipe :auto="4000" style="width: 100%;height: 125px;">
			<mt-swipe-item><img src="../assets/images/1.jpeg" width="100%" /></mt-swipe-item>
			<mt-swipe-item><img src="../assets/images/2.jpeg" width="100%" /></mt-swipe-item>
			<mt-swipe-item><img src="../assets/images/3.jpeg" width="100%" /></mt-swipe-item>
			<mt-swipe-item><img src="../assets/images/4.jpg" width="100%" /></mt-swipe-item>
		</mt-swipe>
		<div style="width: 100%;text-align: center;">
			<mt-button type="default" @click="goButtonTest()">跳转</mt-button>
		</div>
	</div>
</template>

<script>
	export default {
		methods: {
			goBack: function() {
				this.$router.go(-1);
			},
			goButtonTest: function() {
				this.$router.push({path: '/Tab/ButtonTest'});
			}
		}
	};
</script>

<style>

</style>