package org.scaffoldeditor.worldexport.gui.bounds_editor;

import java.io.Closeable;
import java.util.Objects;

import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.RenderInfo;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiElement;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.Dimension;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableDimension;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadablePoint;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;

public class GuiBoundsOverview extends AbstractGuiElement<GuiBoundsOverview> implements Closeable {

    private final OverviewData overviewData;
    private final Identifier texID;
    private final World world;

    private Vec2f panOffset = Vec2f.ZERO;
    private double zoomAmount = 0;

    public GuiBoundsOverview(World world, OverviewData overviewData) {
        Objects.requireNonNull(world);
        Objects.requireNonNull(overviewData);

        this.overviewData = overviewData;
        this.world = world;
        this.texID = getMinecraft().getTextureManager().registerDynamicTexture("overview/", overviewData.getTexture());
        updateTexture();
    }

    public double getZoomAmount() {
        return zoomAmount;
    }

    public void setZoomAmount(double zoomAmount) {
        this.zoomAmount = zoomAmount;
    }

    public Vec2f getPanOffset() {
        return panOffset;
    }

    public void setPanOffset(Vec2f panOffset) {
        this.panOffset = panOffset;
    }

    public void updateTexture() {
        overviewData.updateTexture(world, world.getBottomY(), world.getTopY(), Util.getMainWorkerExecutor());
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        drawMap(renderer, size, renderInfo);
    }

    public double getZoomMultiplier() {
        return Math.pow(2, zoomAmount);
    }

    private void drawMap(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {

        MatrixStack matrices = renderer.getMatrixStack();
        matrices.push();

        ReadablePoint glOffset = renderer.getOpenGlOffset();
        float centerX = size.getWidth() / 2f + glOffset.getX();
        float centerY = size.getHeight() / 2f + glOffset.getY();

        float zoomMultiplier = (float) getZoomMultiplier();

        matrices.translate(centerX, centerY, 0);
        matrices.scale(zoomMultiplier, zoomMultiplier, zoomMultiplier);
        matrices.translate(-centerX, -centerY, 0);
        matrices.translate(panOffset.x, panOffset.y, 0);;

        NativeImage image = overviewData.getTexture().getImage();

        renderer.bindTexture(texID);
        renderer.drawTexturedRect(0, 0, 0, 0, image.getWidth(), image.getHeight());

        matrices.pop();
    }

    @Override
    protected ReadableDimension calcMinSize() {
        return new Dimension(128, 128);
    }

    @Override
    protected GuiBoundsOverview getThis() {
        return this;
    }

    @Override
    public void close() {
        overviewData.close();
    }
    
}
