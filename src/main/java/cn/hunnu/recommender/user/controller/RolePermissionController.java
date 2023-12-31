package cn.hunnu.recommender.user.controller;


import cn.hunnu.recommender.common.Result;
import cn.hunnu.recommender.user.dto.PersonRoleQuery;
import cn.hunnu.recommender.user.dto.RolePermissionQuery;
import cn.hunnu.recommender.user.entity.Permission;
import cn.hunnu.recommender.user.entity.PersonRole;
import cn.hunnu.recommender.user.entity.Role;
import cn.hunnu.recommender.user.entity.RolePermission;
import cn.hunnu.recommender.user.mapper.RolePermissionMapper;
import cn.hunnu.recommender.user.vo.RolePermissionVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色权限关联表 前端控制器
 * </p>
 *
 * @author czj
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/role-permission")
@Api(value = "角色权限关联模块",tags = "角色权限关联模块")
public class RolePermissionController extends userBaseController {
    @ApiOperation(value = "角色权限关联列表",notes = "角色权限关联列表")
    @GetMapping("/list")
    public List<RolePermission> list() {
        return rolePermissionService.list();
    }

    //编辑 新增 根据ID是否存在判断
    @PostMapping("/save")
    @ApiOperation(value = "角色权限关联的新增/修改",notes = "角色权限关联的新增/修改")
    public Result save(@Validated @RequestBody RolePermission rolePermission){
        rolePermissionService.saveOrUpdate(rolePermission);
        return Result.success();
    }

    //根据ID删除用户
    @PostMapping("/delBatch")
    @ApiOperation(value = "删除角色权限关联信息",notes = "删除角色权限关联信息")
    public Result delete(@RequestBody List<Integer> IDS){
        rolePermissionService.removeByIds(IDS);
        return Result.success();
    }

    @PostMapping("/page")
    @ApiOperation(value = "角色权限关联信息查询",notes = "角色权限关联信息查询")
    public Result<Page<RolePermission>> queryPersonInfo(@RequestBody RolePermissionQuery rolePermissionQuery){


        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(RolePermission::getRolePermissionId);

        if(!"".equals(rolePermissionQuery.getPermissionId())&& rolePermissionQuery.getPermissionId()!=null){
            wrapper.eq(RolePermission::getPermissionId, rolePermissionQuery.getPermissionId());
        }

        if(!"".equals(rolePermissionQuery.getRoleId())&& rolePermissionQuery.getRoleId()!=null){
            wrapper.eq(RolePermission::getRoleId, rolePermissionQuery.getRoleId());
        }

        Page<RolePermission> page = rolePermissionService.page(
                new Page<>(
                        rolePermissionQuery.getPageNum(),
                        rolePermissionQuery.getPageSize()
                ),
                wrapper
        );
        return Result.success(page);
    }

    @ApiOperation(value = "查询单个角色权限信息",notes = "查询单个角色权限信息")
    @PostMapping("/rolePermissions")
    public Result findRolePermissions(@RequestBody RolePermissionVO rolePermissionVO) {
        List<Permission> permissions = rolePermissionService.findRolePermissions(rolePermissionVO.getRoleId(),rolePermissionVO.getPermissionName());
        return Result.success(permissions);
    }

    //参数为角色ID和权限ID数组
    @ApiOperation(value = "分配角色权限",notes = "分配角色权限")
    @PostMapping("/reviseRolePermissions")
    public Result reviseRolePermissions(@RequestParam Integer roleId,@RequestBody List<Integer> permissionIds) {
        rolePermissionService.reviseRolePermissions(roleId,permissionIds);
        return Result.success();
    }
}
