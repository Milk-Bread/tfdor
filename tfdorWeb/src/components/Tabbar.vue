<template>
	<div>
		<header class="mint-header is-fixed">
			<div class="mint-header-button is-left">
			</div>
			<h1 class="mint-header-title">{{title}}</h1>
			<div class="mint-header-button is-right">
				<button class="mint-button mint-button--default mint-button--normal">
						<span class="mint-button-icon"><i class="mintui mintui-more"></i></span>
						<label class="mint-button-text"></label>
					</button>
			</div>
		</header>
		<div style="position: absolute;top: 40px;left: 0px;right: 0px;bottom: 55px;overflow:auto;">
			<router-view></router-view>			
		</div>
		<mt-tabbar v-model="selected" fixed>
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
				selected: 'Index',
				title: '首页'
			};
		},
		watch: {
			selected: function(val, oldVal) { // val：选择的菜单ID  oldVal：原来的菜单ID
				if (val === '/Tab/Index') {
					this.title = '首页';
				} else if (val === '/Tab/New') {
					this.title = '新品';
				} else if (val === '/Tab/ShoppingCart') {
					this.title = '购物车';
				} else if (val === '/Tab/Member') {
					this.title = '个人中心';
				}
				this.$router.push({
					path: val
				});
			}
		}
	};
</script>