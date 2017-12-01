<template>
	<div>
		<router-view></router-view>
		<mt-tabbar v-model="selected">
			<mt-tab-item id="/Tab/Index">
				<img slot="icon" src="../assets/images/index.png"> 首页
			</mt-tab-item>
			<mt-tab-item id="/Tab/New">
				<img slot="icon" src="../assets/images/new.png"> 新品
			</mt-tab-item>
			<mt-tab-item id="/Tab/ShoppingCart">
				<img slot="icon" src="../assets/images/shopping-cart.png"> 购物车
			</mt-tab-item>
			<mt-tab-item id="/Tab/Member">
				<img slot="icon" src="../assets/images/member.png"> 个人中心
			</mt-tab-item>
		</mt-tabbar>
	</div>
</template>

<script>
export default {
	data() {
		return {
			selected: 'Index'
		};
	},
	watch: {
		selected: function (val, oldVal) { // val：选择的菜单ID  oldVal：原来的菜单ID
			this.$router.push({path: val});
		}
	}
};
</script>
<style lang="scss">
</style>